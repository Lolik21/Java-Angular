export const
    validationMessages = {
        'login': {
            'required': 'Обязательное поле.',
        },
        'password': {
            'required': 'Обязательное поле.',
            'minlength': 'Пароль должен быть не менее 6 символов.',
            'pattern': 'Пароль может содержать только буквы латинского алфавита и цифры.'
        },
        'passwordConfirm': {
            'required': 'Обязательное поле.',
            'invalidPassword': 'Несовпадение паролей.'
        },
        'email': {
            'required': 'Обязательное поле.',
            'pattern': 'Неверный формат почты.',
        }
    };
