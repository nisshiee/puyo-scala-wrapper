import org.specs2._
class AppSpec extends Specification { def is =

    "������'Hello world'�Ɋւ���e�X�g"                                         ^
                                                                                p^
    "������'Hello world'��"                                                     ^
      "������11�ł��邱��"                                                      ! e1^
      "'Hello'�Ŏn�܂邱��"                                                     ! e2^
      "'world'�ŏI��邱��"                                                     ! e3^
                                                                                end

    def e1 = "Hello world" must have size(11)
    def e2 = "Hello world" must startWith("Hello")
    def e3 = "Hello world" must endWith("world")
}
