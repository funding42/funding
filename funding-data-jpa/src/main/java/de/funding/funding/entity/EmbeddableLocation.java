package de.funding.funding.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableLocation {

  private double longitude;

  private double latitude;

  @Column(name = "longitude", nullable = false)
  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(final double longitude) {
    this.longitude = longitude;
  }

  @Column(name = "latitude", nullable = false)
  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(final double latitude) {
    this.latitude = latitude;
  }
}
