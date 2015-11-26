package t::Payment::Transaction::CardData::set_fields;

use strict;
use warnings;
use Test::More;
use Test::Exception;

require_ok( "Payment::Transaction::CardData" );

my $obj = new_ok( "Payment::Transaction::CardData" );

# 16-digit PAN
ok( $obj->PAN( 4444333322221111 ), "16 digit PAN" );

# 19-digit PAN
ok( $obj->PAN( 1234567890123456789 ), "19 digit PAN" );

# invlid 15-digit PAN
ok( $obj->PAN( 123456789012345 ), "15 digit PAN" );


done_testing();

1;
