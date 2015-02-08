require_all 'stats/'

class DataController < ApplicationController
	def form
		if params[:type] && params[:type] == "t_dist"
			redirect_to "/data/form2"
		end
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

	def outcome

	end
end
