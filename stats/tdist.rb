class Tdist
	$lower_n = 15
	$higher_n = 39
	$skew_bound = 0.5
	attr_accessor :d

	def initialize (d)
		@d = d
	end

	def calculations (tcrit)
		if d.length < lower_n
			return false
		elsif d.length > higher_n
			return false
		end

		sum = 0
		for val in d
			sum += val
		end

		denom = 0
		numer = 0
		avg = sum / d.length
		stdev_sum = 0
		for val in d
			denom += (val-sum/d.length) ** 3
			num += (val-sum/d.length) ** 2
			stdev_sum += (d.length-avg) ** 2
		end
		coeff = 1/d.length
		skew = (coeff * denom) / (coeff * numer) ** 1.5

		if abs(skew) > skew_bound
			return false
		end

		stdev = (1/(d.length-1) * stdev_sum) ** 0.5
		margin_error = tcrit * stdev / d.length ** 0.5
		return margin_error
	end
end
