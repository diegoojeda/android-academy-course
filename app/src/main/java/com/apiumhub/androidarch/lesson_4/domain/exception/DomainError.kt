package com.apiumhub.androidarch.lesson_4.domain.exception

abstract class DomainError : Error()
class NoInternetConnectionException : DomainError()
class InvalidUserIdException : DomainError()