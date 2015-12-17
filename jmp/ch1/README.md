# Chapter 1: Strategy Pattern

## Bank Messaging

This contains a simple framework for conducting bank messaging.

A merchant sends authorisation messages to their acquirer through
a terminal. The terminal is responsible for knowing how to construct
the messages for the acquirer, how to parse the response, and how
to actually send the message.

### Strategy Pattern

The `Payment::BankTerminal` class has two different strategies:

   * `Payment::Message::Strategy`: Used by the terminal to translate
   a purchase/refund/etc. request into a message the acquirer understands. This
   is also responsible for parsing the response from the acquirer. In real life, you would have implementations of
   this role for message formats like APACS30 or ISO8583.
   * `Payment::Comms::CommunicationStrategy`: Used by the terminal
   to make the actual request to the bank. Example implementations
   of this would include a TCP socket strategy, HTTPS, X25, etc.
