import $ from 'jquery'
import validate from 'jquery-validation'

export function handleValidate() {
    $.validator.setDefaults({
            errorElement: "p",
            errorClass: "text-error",
            ignore: ".ignore",
            messages: {
                required: "The field is required."
            },
            highlight: function(element, errorClass, validClass) {
                $(element).addClass(errorClass).removeClass(validClass);
                $(element).focus()
            }
    });
}