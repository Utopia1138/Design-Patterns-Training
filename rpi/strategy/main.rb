require_relative 'data'
require_relative 'formatters'

data = Data.new( JsonFormatter.new )
data.output

data.formatter = XMLFormatter.new
data.output
