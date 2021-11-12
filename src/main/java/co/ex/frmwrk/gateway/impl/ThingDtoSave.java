package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ThingDtoSave implements ThingDto, Serializable {

  static final long serialVersionUID = 2217277525533320357L;

  private Long thingNbr;
  private String description;
  private String fullDescription;
  private BigDecimal price;

  public ThingDtoSave(Long thingNbr, String description, String fullDescription, BigDecimal price) {
    this.thingNbr = thingNbr;
    this.description = description;
    this.fullDescription = fullDescription;
    this.price = price;
  }

  public ThingDtoSave() {}

  public static ThingDtoBuilder builder() {
    return new ThingDtoBuilder();
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof ThingDtoSave)) return false;
    final ThingDtoSave other = (ThingDtoSave) o;
    if (!other.canEqual(this)) return false;
    final Object this$thingNbr = this.getThingNbr();
    final Object other$thingNbr = other.getThingNbr();
    if (this$thingNbr == null ? other$thingNbr != null : !this$thingNbr.equals(other$thingNbr))
      return false;
    final Object this$description = this.getDescription();
    final Object other$description = other.getDescription();
    if (this$description == null
        ? other$description != null
        : !this$description.equals(other$description)) return false;
    final Object this$fullDescription = this.getFullDescription();
    final Object other$fullDescription = other.getFullDescription();
    if (this$fullDescription == null
        ? other$fullDescription != null
        : !this$fullDescription.equals(other$fullDescription)) return false;
    final Object this$price = this.getPrice();
    final Object other$price = other.getPrice();
    return this$price == null ? other$price == null : this$price.equals(other$price);
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ThingDtoSave;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $thingNbr = this.getThingNbr();
    result = result * PRIME + ($thingNbr == null ? 43 : $thingNbr.hashCode());
    final Object $description = this.getDescription();
    result = result * PRIME + ($description == null ? 43 : $description.hashCode());
    final Object $fullDescription = this.getFullDescription();
    result = result * PRIME + ($fullDescription == null ? 43 : $fullDescription.hashCode());
    final Object $price = this.getPrice();
    result = result * PRIME + ($price == null ? 43 : $price.hashCode());
    return result;
  }

  public String toString() {
    return "ThingDto(thingNbr="
        + this.getThingNbr()
        + ", description="
        + this.getDescription()
        + ", fullDescription="
        + this.getFullDescription()
        + ", price="
        + this.getPrice()
        + ")";
  }

  public Long getThingNbr() {
    return this.thingNbr;
  }

  public void setThingNbr(Long thingNbr) {
    this.thingNbr = thingNbr;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFullDescription() {
    return this.fullDescription;
  }

  public void setFullDescription(String fullDescription) {
    this.fullDescription = fullDescription;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public static class ThingDtoBuilder {
    private Long thingNbr;
    private String description;
    private String fullDescription;
    private BigDecimal price;

    ThingDtoBuilder() {}

    public ThingDtoSave.ThingDtoBuilder thingNbr(Long thingNbr) {
      this.thingNbr = thingNbr;
      return this;
    }

    public ThingDtoSave.ThingDtoBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ThingDtoSave.ThingDtoBuilder fullDescription(String fullDescription) {
      this.fullDescription = fullDescription;
      return this;
    }

    public ThingDtoSave.ThingDtoBuilder price(BigDecimal price) {
      this.price = price;
      return this;
    }

    public ThingDtoSave build() {
      return new ThingDtoSave(thingNbr, description, fullDescription, price);
    }

    public String toString() {
      return "ThingDto.ThingDtoBuilder(thingNbr="
          + this.thingNbr
          + ", description="
          + this.description
          + ", fullDescription="
          + this.fullDescription
          + ", price="
          + this.price
          + ")";
    }
  }
}
