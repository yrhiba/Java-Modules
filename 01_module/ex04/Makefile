JAVAC = javac

JAVA_SOURCES = \
	Program.java \
	IllegalTransactionException.java \
	Transaction.java \
	TransactionNotFoundException.java \
	TransactionsLinkedList.java \
	TransactionsList.java \
	TransactionsService.java \
	User.java \
	UserIdsGenerator.java \
	UserNotFoundException.java \
	UsersArrayList.java \
	UsersList.java

JAVA_CLASSES = $(JAVA_SOURCES:.java=.class)

all: $(JAVA_CLASSES)

%.class: %.java
	$(JAVAC) $<

run: all
	java Program

clean:
	rm -f *.class

.PHONY: all run clean
