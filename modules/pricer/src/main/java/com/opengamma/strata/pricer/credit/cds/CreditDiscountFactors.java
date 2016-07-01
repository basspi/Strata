package com.opengamma.strata.pricer.credit.cds;

import java.time.LocalDate;
import java.util.Optional;

import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.collect.array.DoubleArray;
import com.opengamma.strata.data.MarketDataName;
import com.opengamma.strata.market.MarketDataView;
import com.opengamma.strata.market.curve.Curve;
import com.opengamma.strata.market.curve.CurveName;
import com.opengamma.strata.market.param.CurrencyParameterSensitivities;
import com.opengamma.strata.market.param.CurrencyParameterSensitivity;
import com.opengamma.strata.market.param.ParameterPerturbation;
import com.opengamma.strata.market.param.ParameterizedData;
import com.opengamma.strata.pricer.DiscountFactors;
import com.opengamma.strata.pricer.ZeroRateSensitivity;

public interface CreditDiscountFactors
    extends MarketDataView, ParameterizedData {

  // TODO toDiscountFactors fromDiscountFactors

  /**
   * Gets the currency.
   * <p>
   * The currency that discount factors are provided for.
   * 
   * @return the currency
   */
  public abstract Currency getCurrency();

  /**
   * Finds the market data structure underlying this instance with the specified name.
   * <p>
   * This is most commonly used to find a {@link Curve} using a {@link CurveName}.
   * If the market data cannot be found, empty is returned.
   * 
   * @param <T>  the type of the market data value
   * @param name  the name to find
   * @return the market data value, empty if not found
   */
  public abstract <T> Optional<T> findData(MarketDataName<T> name);

  //-------------------------------------------------------------------------
  @Override
  public abstract DiscountFactors withParameter(int parameterIndex, double newValue);

  @Override
  public abstract DiscountFactors withPerturbation(ParameterPerturbation perturbation);

  //-------------------------------------------------------------------------
  /**
   * Calculates the relative time between the valuation date and the specified date.
   * <p>
   * The {@code double} value returned from this method is used as the input to other methods.
   * It is typically calculated from a {@link DayCount}.
   * 
   * @param date  the date
   * @return  the year fraction
   * @throws RuntimeException if it is not possible to convert dates to relative times
   */
  public double relativeYearFraction(LocalDate date);

  /**
   * Gets the discount factor for the specified date.
   * <p>
   * The discount factor represents the time value of money for the specified currency
   * when comparing the valuation date to the specified date.
   * <p>
   * If the valuation date is on or after the specified date, the discount factor is 1.
   * 
   * @param date  the date to discount to
   * @return the discount factor
   * @throws RuntimeException if the value cannot be obtained
   */
  public default double discountFactor(LocalDate date) {
    double yearFraction = relativeYearFraction(date);
    return discountFactor(yearFraction);
  }

  /**
   * Gets the discount factor for specified year fraction.
   * <p>
   * The year fraction must be based on {@code #relativeYearFraction(LocalDate)}.
   * 
   * @param yearFraction  the year fraction 
   * @return the discount factor
   * @throws RuntimeException if the value cannot be obtained
   */
  public abstract double discountFactor(double yearFraction);


  /**
   * Gets the continuously compounded zero rate for the specified date.
   * <p>
   * The continuously compounded zero rate is coherent to {@link #discountFactor(LocalDate)} along with 
   * year fraction which is computed internally in each implementation. 
   * 
   * @param date  the date to discount to
   * @return the zero rate
   * @throws RuntimeException if the value cannot be obtained
   */
  public default double zeroRate(LocalDate date) {
    double yearFraction = relativeYearFraction(date);
    return zeroRate(yearFraction);
  }

  /**
   * Gets the continuously compounded zero rate for specified year fraction.
   * <p>
   * The year fraction must be based on {@code #relativeYearFraction(LocalDate)}.
   * 
   * @param yearFraction  the year fraction 
   * @return the zero rate
   * @throws RuntimeException if the value cannot be obtained
   */
  public abstract double zeroRate(double yearFraction);

  public default double zeroRateYearFraction(double yearFraction) {
    return zeroRate(yearFraction) * yearFraction;
  }

  //-------------------------------------------------------------------------
  /**
   * Calculates the zero rate point sensitivity at the specified date.
   * <p>
   * This returns a sensitivity instance referring to the zero rate sensitivity of the
   * points that were queried in the market data.
   * The sensitivity typically has the value {@code (-discountFactor * yearFraction)}.
   * The sensitivity refers to the result of {@link #discountFactor(LocalDate)}.
   * 
   * @param date  the date to discount to
   * @return the point sensitivity of the zero rate
   * @throws RuntimeException if the result cannot be calculated
   */
  public default ZeroRateSensitivity zeroRatePointSensitivity(LocalDate date) {
    double yearFraction = relativeYearFraction(date);
    return zeroRatePointSensitivity(yearFraction);
  }

  /**
   * Calculates the zero rate point sensitivity at the specified year fraction.
   * <p>
   * This returns a sensitivity instance referring to the zero rate sensitivity of the
   * points that were queried in the market data.
   * The sensitivity typically has the value {@code (-discountFactor * yearFraction)}.
   * The sensitivity refers to the result of {@link #discountFactor(LocalDate)}.
   * <p>
   * The year fraction must be based on {@code #relativeYearFraction(LocalDate)}.
   * 
   * @param yearFraction  the year fraction
   * @return the point sensitivity of the zero rate
   * @throws RuntimeException if the result cannot be calculated
   */
  public default ZeroRateSensitivity zeroRatePointSensitivity(double yearFraction) {
    return zeroRatePointSensitivity(yearFraction, getCurrency());
  }

  /**
   * Calculates the zero rate point sensitivity at the specified date specifying the currency of the sensitivity.
   * <p>
   * This returns a sensitivity instance referring to the zero rate sensitivity of the
   * points that were queried in the market data.
   * The sensitivity typically has the value {@code (-discountFactor * yearFraction)}.
   * The sensitivity refers to the result of {@link #discountFactor(LocalDate)}.
   * <p>
   * This method allows the currency of the sensitivity to differ from the currency of the market data.
   * 
   * @param date  the date to discount to
   * @param sensitivityCurrency  the currency of the sensitivity
   * @return the point sensitivity of the zero rate
   * @throws RuntimeException if the result cannot be calculated
   */
  public default ZeroRateSensitivity zeroRatePointSensitivity(LocalDate date, Currency sensitivityCurrency) {
    double yearFraction = relativeYearFraction(date);
    return zeroRatePointSensitivity(yearFraction, sensitivityCurrency);
  }

  /**
   * Calculates the zero rate point sensitivity at the specified year fraction specifying the currency of the sensitivity.
   * <p>
   * This returns a sensitivity instance referring to the zero rate sensitivity of the
   * points that were queried in the market data.
   * The sensitivity typically has the value {@code (-discountFactor * yearFraction)}.
   * The sensitivity refers to the result of {@link #discountFactor(LocalDate)}.
   * <p>
   * This method allows the currency of the sensitivity to differ from the currency of the market data.
   * <p>
   * The year fraction must be based on {@code #relativeYearFraction(LocalDate)}.
   * 
   * @param yearFraction  the year fraction
   * @param sensitivityCurrency  the currency of the sensitivity
   * @return the point sensitivity of the zero rate
   * @throws RuntimeException if the result cannot be calculated
   */
  public abstract ZeroRateSensitivity zeroRatePointSensitivity(double yearFraction, Currency sensitivityCurrency);

  //-------------------------------------------------------------------------
  /**
   * Calculates the parameter sensitivity from the point sensitivity.
   * <p>
   * This is used to convert a single point sensitivity to parameter sensitivity.
   * The calculation typically involves multiplying the point and unit sensitivities.
   * 
   * @param pointSensitivity  the point sensitivity to convert
   * @return the parameter sensitivity
   * @throws RuntimeException if the result cannot be calculated
   */
  public abstract CurrencyParameterSensitivities parameterSensitivity(ZeroRateSensitivity pointSensitivity);

  /**
   * Creates the parameter sensitivity when the sensitivity values are known.
   * <p>
   * In most cases, {@link #parameterSensitivity(ZeroRateSensitivity)} should be used and manipulated.
   * However, it can be useful to create parameter sensitivity from pre-computed sensitivity values.
   * <p>
   * There will typically be one {@link CurrencyParameterSensitivity} for each underlying data
   * structure, such as a curve. For example, if the discount factors are based on a single discount
   * curve, then there will be one {@code CurrencyParameterSensitivity} in the result.
   * 
   * @param currency  the currency
   * @param sensitivities  the sensitivity values, which must match the parameter count
   * @return the parameter sensitivity
   * @throws RuntimeException if the result cannot be calculated
   */
  public abstract CurrencyParameterSensitivities createParameterSensitivity(Currency currency, DoubleArray sensitivities);

  //-------------------------------------------------------------------------
  public DoubleArray getParameterKeys();

}
