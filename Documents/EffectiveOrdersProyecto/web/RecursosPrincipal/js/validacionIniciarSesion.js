$().ready(function () {

    $('#formularioInicioSesion').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            documento: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un documento v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'Solo n\u00FAmeros'
                    }
                }
            },
            contrasena: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese una contrase\u00F1a v\u00E1lida'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9]+$/,
                        message: 'No se aceptan car\u00E1cteres especiales'
                    }
                }
            }
        }
    });
});