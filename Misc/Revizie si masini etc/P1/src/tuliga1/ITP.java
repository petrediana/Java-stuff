package tuliga1;

import java.util.Date;

public class ITP extends Revizie implements Comparable<ITP>, Cloneable {
	private Date datal;
	private boolean revenire;

	public ITP() {
	}

	public ITP(int km, double taxa, String firma, Date datal, boolean revenire) throws Exception {
		super(km, taxa, firma);
		this.datal = datal;
		this.revenire = revenire;
	}

	public Date getDatal() {
		return datal;
	}

	public void setDatal(Date datal) {
		this.datal = datal;
	}

	public boolean isRevenire() {
		return revenire;
	}

	public void setRevenire(boolean revenire) {
		this.revenire = revenire;
	}

	@Override
	public String toString() {
		return "ITP{" +
				"datal=" + datal +
				", revenire=" + revenire +
				'}' + super.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ITP itp = (ITP) o;

		return datal != null ? datal.equals(itp.datal) : itp.datal == null;
	}

	@Override
	public int hashCode() {
		return datal != null ? datal.hashCode() : 0;
	}

	@Override
	public int compareTo(ITP o) {
		return this.datal.compareTo(o.datal);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
