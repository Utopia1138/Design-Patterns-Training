package Payment::Merchant;
######################################
# A simple representation of a merchant.
# So far, a merchant only has a name.
#######################################
use strict;
use warnings;

use Moose;

has 'name' => (
  is => 'ro',
  isa => 'Str',
);

no Moose;
__PACKAGE__->meta->make_immutable;
