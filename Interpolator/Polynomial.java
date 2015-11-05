public class Polynomial {
	int[] coeff;
	int degree;

	public Polynomial(int a, int b) {
		coeff = new int[b + 1];
		coeff[b] = a;
		degree = getDegree();
	}

	public int getDegree() {
		int deg = 0;
		for (int i = 0; i < coeff.length; i++) {
			if (coeff[i] != 0) deg = i;
		}
		return deg;
	}

	public Polynomial add(Polynomial b) {
		Polynomial c = new Polynomial(0, Math.max(this.degree, b.degree));
		for (int i = 0; i <= this.degree; i++) {
			c.coeff[i] += this.coeff[i];
		}
		for (int j = 0; j <= b.degree; j++) {
			c.coeff[j] += b.coeff[j];
		}
		c.degree = c.getDegree();
		return c;
	}

	public Polynomial sub(Polynomial b) {
		Polynomial c = new Polynomial(0, Math.max(this.degree, b.degree));
		for (int i = 0; i <= this.degree; i++) {
			c.coeff[i] += this.coeff[i];
		}
		for (int j = 0; j <= b.degree; j++) {
			c.coeff[j] -= b.coeff[j];
		}
		c.degree = c.getDegree();
		return c;
	}

	public Polynomial multiply(Polynomial b) {
		Polynomial c = new Polynomial(0, this.degree + b.degree);
		for (int i = 0; i <= this.degree; i++) {
			for (int j = 0; j <= b.degree; j++) {
				c.coeff[i + j] += (this.coeff[i] * b.coeff[j]);
			}
		}
		c.degree = c.getDegree();
		return c;
	}

	public String toString() {
        if (degree ==  0) return "" + coeff[0];
        if (degree ==  1) return coeff[1] + "x + " + coeff[0];
        String s = coeff[degree] + "x^" + degree;
        for (int i = degree-1; i >= 0; i--) {
            if      (coeff[i] == 0) continue;
            else if (coeff[i]  > 0) s = s + " + " + ( coeff[i]);
            else if (coeff[i]  < 0) s = s + " - " + (-coeff[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

}