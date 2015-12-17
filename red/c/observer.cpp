#include <iostream>
#include <vector>
#include <string>

using namespace std;

template <typename T>
struct Observer {
	virtual void update( T& ) = 0;
	virtual ~Observer() {}
};

template <typename T>
class Observable {
private:
	vector<Observer<T>*> observers;
public:
	Observable& bind(Observer<T>& observer) {
		this->observers.push_back(&observer);
		return *this;
	}

	void notify(T& message) {
		for ( Observer<T>* observer : observers ) {
			observer->update(message);
		}
	}

	virtual ~Observable() {}
};

class Message : public Observable<string> {
private:
	string message;
public:
	Message& operator=(string mess) {
		this->message = mess;
		this->notify(this->message);
		return *this;
	}
};

class Int : public Observable<int> {
private:
	int x;
public:
	Int(int x) : x(x) {}

	Int& operator=(int x) {
		this->x = x;
		this->notify(this->x);
		return *this;
	}
};

struct Printer : public Observer<string>, public Observer<int> {
	virtual void update(string& message) {
		cout << "Received message: " << message << endl;
	}

	virtual void update(int& x) {
		cout << "Integer of: " << x << endl;
	}
};

int main(int argc, char* argv[]) {
	Printer print;

	Message mess;
	mess.bind(print);

	Int x = 50;
	x.bind(print);
	
	mess = "Hello, World";
	x = 25;

	return 0;
}
