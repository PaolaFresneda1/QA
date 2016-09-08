$().ready(function () {

    $('#formulario').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-check-circle', invalid: 'fa fa-exclamation-triangle', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            nombreUsuario: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un nombre v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ]+$/,
                        message: 'S\u00F3lo min\u00FAsculas, sin n\u00FAmeros o caracteres especiales'
                    },
                    stringLength: {
                        min: 3,
                        message: 'M\u00EDnimo 3 y m\u00E1ximo 15 caracteres'
                    }
                }
            },
            apellidoUsuario: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un apellido v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ]+$/,
                        message: 'S\u00F3lo min\u00FAsculas, sin n\u00FAmeros o caracteres especiales'
                    },
                    stringLength: {
                        min: 3,
                        message: 'M\u00EDnimo 3 y m\u00E1ximo 15 caracteres'
                    }
                }
            },
             correoUsuario: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un correo v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/,
                        message: 'El campo no tiene formato de correo electronico'
                    },
                    stringLength: {
                        min: 5,
                        message: 'M\u00EDnimo 5 caracteres'
                    }
                }
            },
            asuntoContactenos: {
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un asunto v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ,.]+$/,
                        message: 'S\u00F3lo letras, sin n\u00FAmeros'
                    },
                    stringLength: {
                        max: 50,
                        message: 'M\u00E1ximo 50 caracteres'
                    }
                }
            },
            mensajeContactenos: {
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un mensaje v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ,.;:]+$/,
                        message: 'S\u00F3lo letras, sin n\u00FAmeros'
                    },
                    stringLength: {
                        min: 20,
                        message: 'M\u00EDnimo 20 caracteres'
                    }
                }
            }
            
        }
        
    });
    
});
