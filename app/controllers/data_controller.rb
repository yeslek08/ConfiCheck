require_all 'stats/'

class DataController < ApplicationController
	attr_accessor :type
	attr_accessor :srs
	attr_accessor :indep
	attr_accessor :d
	attr_accessor :samp_size
	attr_accessor :pop_size
	attr_accessor :conf_level
	attr_accessor :crit
	def form
		@type = type
		@srs = srs
		@indep = indep
		@d = d
		if @type == "tdist"
			@samp_size = d.length
		else
			@samp_size = samp_size
		end
		@pop_size = pop_size
		@conf_level = conf_level
		@crit = crit
	end

	def parse
		t = nil
		if @type == "tdist"
			t = Tdist.new(@d)
			return t.calculations(@crit)
		else
			t = Phat.new(@samp_size, @d)
			return t.calculations(@crit)
		end
	end
end
