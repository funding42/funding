package de.funding.funding.entity;

public class Location {

	private double breitengrad;
	private double laengengrad;

	public Location() {
	}

	public Location(final double breitengrad, final double laengengrad) {
		super();
		this.breitengrad = breitengrad;
		this.laengengrad = laengengrad;
	}

	public double getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(final double breitengrad) {
		this.breitengrad = breitengrad;
	}

	public double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(final double laengengrad) {
		this.laengengrad = laengengrad;
	}

}
