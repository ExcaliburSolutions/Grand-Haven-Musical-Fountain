package application;

public class Addresses {
	private String name;

	public Addresses(String name) {
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Addresses player = (Addresses) o;

		if (name != null ? !name.equals(player.name) : player.name != null) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return name != null ? name.hashCode() : 0;
	}
}
