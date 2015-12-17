class Data
  attr_reader :stuff
  attr_accessor :formatter

  def initialize( formatter )
    @stuff = { title, 'The Title' }
    @formatter = formatter)
  end

  def output
    formatter.output( self )
  end
end
