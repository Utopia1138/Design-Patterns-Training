package Payment::Merchant;

use strict;
use warnings;

use Moose;

has 'name' => (
  is => 'ro',
  isa => 'Str',
);

no Moose;
__PACKAGE__->meta->make_immutable;
