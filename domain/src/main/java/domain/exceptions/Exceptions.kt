package domain.exceptions

class CurrentUserNotFoundException(message: String? = null) : IllegalAccessException(message)
class UserNotFoundException(message: String? = null) : IllegalAccessException(message)
class PinNotDefinedException(message: String? = null) : IllegalAccessException(message)
class InvalidEmailException(message: String? = null) : IllegalAccessException(message)
class InvalidPasswordException(message: String? = null) : IllegalAccessException(message)
class FieldNotDefinedException(name: String) : IllegalAccessException("Paramether $name not defined")
class NoteNotFoundException(message: String? = null) : IllegalAccessException(message)