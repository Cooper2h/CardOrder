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
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowErrorInvalidNameField() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Anatoly Wasserman");
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneField() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("+798621465565");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneFieldWithWord() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("Wasserman");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldShowErrorInvalidNumberTelephoneFieldEmpty() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldShowErrorInvalidTermsAgreement() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Анатолий Вассерман");
        $("[data-test-id=phone] input").setValue("+79862146556");
        $("[type=button]").click();
        $(".input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    public void shouldShowErrorInvalidValueFieldsEmpty() {
        $(".form-field_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
}