class JsonFormatter
  def output( data )
    puts '{'
    data.stuff.each do |key, value|
      puts "\"#{key}\":\"#{value}\""
    end
    puts '}'
  end
end

class XMLFormatter
  def output( data )
    puts '<?xml version="1.0"?>'
    data.stuff.each do |key, value|
      puts "<#{key}>#{value}</#{key}>"
    end
  end
end
