package potemkin.i.yu;

public class Address implements Comparable<Address> {
	public String street;
	public String city;
	public int house;
	public int flat;

	public Address(String street, String city, int house, int flat) {
		this.street = street;
		this.city = city;
		this.house = house;
		this.flat = flat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + flat;
		result = prime * result + house;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (flat != other.flat)
			return false;
		if (house != other.house)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address: street=" + street + ", city=" + city + ", house=" + house + ", flat=" + flat;
	}

	@Override
	public int compareTo(Address o) {
		return (this.city.compareTo(o.city) != 0 ? this.city.compareTo(o.city)
				: (this.street.compareTo(o.street) != 0 ? this.street.compareTo(o.street)
						: (this.house - o.house != 0 ? this.house - o.house : this.flat - o.flat)));
	}
}
