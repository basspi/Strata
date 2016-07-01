package com.opengamma.strata.pricer.credit.cds;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.StandardId;
import com.opengamma.strata.basics.currency.Currency;
import com.opengamma.strata.basics.currency.FxRateProvider;
import com.opengamma.strata.market.sensitivity.MutablePointSensitivities;
import com.opengamma.strata.market.sensitivity.PointSensitivity;
import com.opengamma.strata.market.sensitivity.PointSensitivityBuilder;
import com.opengamma.strata.pricer.ZeroRateSensitivity;

@BeanDefinition(builderScope = "private")
public final class RecoveryRateSensitivity
    implements PointSensitivity, PointSensitivityBuilder, ImmutableBean, Serializable {

  /**
   * The currency of the curve for which the sensitivity is computed.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency curveCurrency;
  /**
   * The time that was queried, expressed as a year fraction.
   */
  @PropertyDefinition
  private final double yearFraction;
  /**
   * The currency of the sensitivity.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final Currency currency;

  @PropertyDefinition(validate = "notNull")
  private final StandardId legalEntityId;
  /**
   * The value of the sensitivity.
   */
  @PropertyDefinition(overrideGet = true)
  private final double sensitivity;

  //-------------------------------------------------------------------------

  public static RecoveryRateSensitivity of(
      Currency currency,
      double yearFraction,
      StandardId legalEntityId,
      double sensitivity) {

    return of(currency, yearFraction, currency, legalEntityId, sensitivity);
  }


  public static RecoveryRateSensitivity of(
      ZeroRateSensitivity zeroRateSensitivity,
      StandardId legalEntityId) {

    return of(
        zeroRateSensitivity.getCurveCurrency(),
        zeroRateSensitivity.getYearFraction(),
        zeroRateSensitivity.getCurrency(),
        legalEntityId,
        zeroRateSensitivity.getSensitivity());
  }


  public static RecoveryRateSensitivity of(
      Currency curveCurrency,
      double yearFraction,
      Currency sensitivityCurrency,
      StandardId legalEntityId,
      double sensitivity) {

    return new RecoveryRateSensitivity(curveCurrency, yearFraction, sensitivityCurrency, legalEntityId, sensitivity);
  }

  //-------------------------------------------------------------------------
  @Override
  public RecoveryRateSensitivity convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RecoveryRateSensitivity withCurrency(Currency currency) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PointSensitivityBuilder mapSensitivity(DoubleUnaryOperator operator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PointSensitivityBuilder normalize() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MutablePointSensitivities buildInto(MutablePointSensitivities combination) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PointSensitivityBuilder cloned() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PointSensitivity withSensitivity(double sensitivity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int compareKey(PointSensitivity other) {
    // TODO Auto-generated method stub
    return 0;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RecoveryRateSensitivity}.
   * @return the meta-bean, not null
   */
  public static RecoveryRateSensitivity.Meta meta() {
    return RecoveryRateSensitivity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RecoveryRateSensitivity.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private RecoveryRateSensitivity(
      Currency curveCurrency,
      double yearFraction,
      Currency currency,
      StandardId legalEntityId,
      double sensitivity) {
    JodaBeanUtils.notNull(curveCurrency, "curveCurrency");
    JodaBeanUtils.notNull(currency, "currency");
    JodaBeanUtils.notNull(legalEntityId, "legalEntityId");
    this.curveCurrency = curveCurrency;
    this.yearFraction = yearFraction;
    this.currency = currency;
    this.legalEntityId = legalEntityId;
    this.sensitivity = sensitivity;
  }

  @Override
  public RecoveryRateSensitivity.Meta metaBean() {
    return RecoveryRateSensitivity.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency of the curve for which the sensitivity is computed.
   * @return the value of the property, not null
   */
  public Currency getCurveCurrency() {
    return curveCurrency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the time that was queried, expressed as a year fraction.
   * @return the value of the property
   */
  public double getYearFraction() {
    return yearFraction;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency of the sensitivity.
   * @return the value of the property, not null
   */
  @Override
  public Currency getCurrency() {
    return currency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the legalEntityId.
   * @return the value of the property, not null
   */
  public StandardId getLegalEntityId() {
    return legalEntityId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the value of the sensitivity.
   * @return the value of the property
   */
  @Override
  public double getSensitivity() {
    return sensitivity;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      RecoveryRateSensitivity other = (RecoveryRateSensitivity) obj;
      return JodaBeanUtils.equal(curveCurrency, other.curveCurrency) &&
          JodaBeanUtils.equal(yearFraction, other.yearFraction) &&
          JodaBeanUtils.equal(currency, other.currency) &&
          JodaBeanUtils.equal(legalEntityId, other.legalEntityId) &&
          JodaBeanUtils.equal(sensitivity, other.sensitivity);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(curveCurrency);
    hash = hash * 31 + JodaBeanUtils.hashCode(yearFraction);
    hash = hash * 31 + JodaBeanUtils.hashCode(currency);
    hash = hash * 31 + JodaBeanUtils.hashCode(legalEntityId);
    hash = hash * 31 + JodaBeanUtils.hashCode(sensitivity);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("RecoveryRateSensitivity{");
    buf.append("curveCurrency").append('=').append(curveCurrency).append(',').append(' ');
    buf.append("yearFraction").append('=').append(yearFraction).append(',').append(' ');
    buf.append("currency").append('=').append(currency).append(',').append(' ');
    buf.append("legalEntityId").append('=').append(legalEntityId).append(',').append(' ');
    buf.append("sensitivity").append('=').append(JodaBeanUtils.toString(sensitivity));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RecoveryRateSensitivity}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code curveCurrency} property.
     */
    private final MetaProperty<Currency> curveCurrency = DirectMetaProperty.ofImmutable(
        this, "curveCurrency", RecoveryRateSensitivity.class, Currency.class);
    /**
     * The meta-property for the {@code yearFraction} property.
     */
    private final MetaProperty<Double> yearFraction = DirectMetaProperty.ofImmutable(
        this, "yearFraction", RecoveryRateSensitivity.class, Double.TYPE);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> currency = DirectMetaProperty.ofImmutable(
        this, "currency", RecoveryRateSensitivity.class, Currency.class);
    /**
     * The meta-property for the {@code legalEntityId} property.
     */
    private final MetaProperty<StandardId> legalEntityId = DirectMetaProperty.ofImmutable(
        this, "legalEntityId", RecoveryRateSensitivity.class, StandardId.class);
    /**
     * The meta-property for the {@code sensitivity} property.
     */
    private final MetaProperty<Double> sensitivity = DirectMetaProperty.ofImmutable(
        this, "sensitivity", RecoveryRateSensitivity.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "curveCurrency",
        "yearFraction",
        "currency",
        "legalEntityId",
        "sensitivity");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1303639584:  // curveCurrency
          return curveCurrency;
        case -1731780257:  // yearFraction
          return yearFraction;
        case 575402001:  // currency
          return currency;
        case 866287159:  // legalEntityId
          return legalEntityId;
        case 564403871:  // sensitivity
          return sensitivity;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends RecoveryRateSensitivity> builder() {
      return new RecoveryRateSensitivity.Builder();
    }

    @Override
    public Class<? extends RecoveryRateSensitivity> beanType() {
      return RecoveryRateSensitivity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code curveCurrency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> curveCurrency() {
      return curveCurrency;
    }

    /**
     * The meta-property for the {@code yearFraction} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> yearFraction() {
      return yearFraction;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> currency() {
      return currency;
    }

    /**
     * The meta-property for the {@code legalEntityId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> legalEntityId() {
      return legalEntityId;
    }

    /**
     * The meta-property for the {@code sensitivity} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> sensitivity() {
      return sensitivity;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1303639584:  // curveCurrency
          return ((RecoveryRateSensitivity) bean).getCurveCurrency();
        case -1731780257:  // yearFraction
          return ((RecoveryRateSensitivity) bean).getYearFraction();
        case 575402001:  // currency
          return ((RecoveryRateSensitivity) bean).getCurrency();
        case 866287159:  // legalEntityId
          return ((RecoveryRateSensitivity) bean).getLegalEntityId();
        case 564403871:  // sensitivity
          return ((RecoveryRateSensitivity) bean).getSensitivity();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code RecoveryRateSensitivity}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<RecoveryRateSensitivity> {

    private Currency curveCurrency;
    private double yearFraction;
    private Currency currency;
    private StandardId legalEntityId;
    private double sensitivity;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1303639584:  // curveCurrency
          return curveCurrency;
        case -1731780257:  // yearFraction
          return yearFraction;
        case 575402001:  // currency
          return currency;
        case 866287159:  // legalEntityId
          return legalEntityId;
        case 564403871:  // sensitivity
          return sensitivity;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1303639584:  // curveCurrency
          this.curveCurrency = (Currency) newValue;
          break;
        case -1731780257:  // yearFraction
          this.yearFraction = (Double) newValue;
          break;
        case 575402001:  // currency
          this.currency = (Currency) newValue;
          break;
        case 866287159:  // legalEntityId
          this.legalEntityId = (StandardId) newValue;
          break;
        case 564403871:  // sensitivity
          this.sensitivity = (Double) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public RecoveryRateSensitivity build() {
      return new RecoveryRateSensitivity(
          curveCurrency,
          yearFraction,
          currency,
          legalEntityId,
          sensitivity);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("RecoveryRateSensitivity.Builder{");
      buf.append("curveCurrency").append('=').append(JodaBeanUtils.toString(curveCurrency)).append(',').append(' ');
      buf.append("yearFraction").append('=').append(JodaBeanUtils.toString(yearFraction)).append(',').append(' ');
      buf.append("currency").append('=').append(JodaBeanUtils.toString(currency)).append(',').append(' ');
      buf.append("legalEntityId").append('=').append(JodaBeanUtils.toString(legalEntityId)).append(',').append(' ');
      buf.append("sensitivity").append('=').append(JodaBeanUtils.toString(sensitivity));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
