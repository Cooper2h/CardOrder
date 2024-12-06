package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderNegativeTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldShowErrorInvalidNameFieldEmpty() {
        $("[data-test-id=name] input").setValue(""); // Поле имени пустое
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Ищем ошибку по конкретному полю "Имя"
        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowErrorInvalidNameField() {
        $("[data-test-id=name] input").setValue("Anatoly Wasserman"); // Некорректное имя
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Проверяем сообщение об ошибке для поля "Имя"
        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneField() {
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("+798621465565"); // Некорректный номер телефона
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Проверяем сообщение об ошибке для телефона
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneFieldWithWord() {
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("Wasserman"); // Текст вместо номера
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Проверяем сообщение об ошибке для телефона
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneFieldEmpty() {
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue(""); // Поле телефона пустое
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Проверяем сообщение об ошибке для телефона
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowErrorInvalidTermsAgreement() {
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[type=button]").click(); // Не ставим галочку "Согласие"

        // Проверяем сообщение об ошибке для чекбокса
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    public void shouldShowErrorInvalidValueFieldsEmpty() {
        $("[data-test-id=name] input").setValue(""); // Оба поля пустые
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();

        // Проверяем сообщение об ошибке для имени
        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

        // Проверяем сообщение об ошибке для телефона
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
}
