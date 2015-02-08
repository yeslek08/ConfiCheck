class Phat
	$higher = 10
	attr_accessor :p
	attr_accessor :n

	def initialize (n, p)
		@n = n
		@p = p
	end

	def normality
		if n * p >= higher && n * (1-p) >= higher
			return true
		end
		return false
	end

	def calc_conf(zcrit)
		margin_error = (p * (1-p)/n) ** 0.5
		return margin_error
	end

	def calculations (zcrit)
		if !normality
			return false
		end
		return calc_conf(zcrit)
	end
end
