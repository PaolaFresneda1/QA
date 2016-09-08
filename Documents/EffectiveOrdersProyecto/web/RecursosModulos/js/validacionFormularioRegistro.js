$().ready(function() {
  
    FormValidation.Validator.mayorEdad = {
        validate: function(validator, $field, options) {
            var value = $field.val();
            var fechanacimiento = moment(value, "DD-MM-YYYY");
          
            if(!fechanacimiento.isValid())
                return false;
          
            var years = moment().diff(fechanacimiento, 'years');
          
            return years >= 18;
        }
    }


    $('#formularioActualizar').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            nombre: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un nombre v\u00E1lido '

                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ]+$/,
                        message: 'S\u00F3lo letras, sin n\u00FAmeros o car\u00E1cteres especiales'
                    },
                    stringLength: {
                        min: 3,
                        message: 'M\u00EDnimo 3 car\u00E1cteres'
                    }
                }
            },
            apellido: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un apellido v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ]+$/,
                        message: 'S\u00F3lo letras, sin n\u00FAmeros o car\u00E1cteres especiales'
                    },
                    stringLength: {
                        min: 3,
                        message: 'M\u00EDnimo 3 car\u00E1cteres'
                    }
                }
            },
            documento: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un documento v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'S\u00F3lo n\u00FAmeros'
                    },
                    stringLength: {
                        min: 10,
                        message: 'M\u00EDnimo 10 car\u00E1cteres'
                    }
                }
            },
            correo: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un correo v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,3})$/,
                        message: 'Ingrese un formato correcto de correo (asd@hotm.com)'
                    },
                    stringLength: {
                        min: 5,
                        message: 'M\u00EDnimo 5 car\u00E1cteres'
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
                    },
                    stringLength: {
                        min: 5,
                        message: 'M\u00EDnimo 5 car\u00E1cteres'
                    }
                }
            },
            repetirContrasena: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Las contrase\u00F1as no coinciden'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9]+$/,
                        message: 'No se aceptan car\u00E1cteres especiales'
                    },
                    stringLength: {
                        min: 5,
                        message: 'M\u00EDnimo 5 car\u00E1cteres'
                    },
                    identical: {
                        field: 'contrasena',
                        message: 'Las contrase\u00F1as no coinciden'
                    }
                }
            },
            direccion: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese una direcci\u00F3n v\u00E1lida'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ#]+$/,
                        message: 'No se aceptan car\u00E1cteres especiales'
                    },
                    stringLength: {
                        min: 10,
                        message: 'M\u00EDnimo 10 car\u00E1cteres'
                    }
                }
            },
            tel: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un t\u00E9lefono v\u00E1lido'
                    },
                    regexp: {
                        regexp: /^[0-9-]+$/,
                        message: 'S\u00F3lo n\u00FAmeros'
                    },
                    stringLength: {
                        min: 7,
                        message: 'M\u00EDnimo 7 car\u00E1cteres'
                    }
                }

            },
            ciudad: {
                validators: {
                    callback: {
                        message: 'Debe elegir su ciudad',
                        callback: function (value, validator, $field) {
                            // Get the selected options
                            var options = validator.getFieldElements('ciudad').val();
                            return (options !== null && options.length >= 1 && options.length <= 4);
                        }
                    }
                }
            },
               genero: {
                validators: {
                    callback: {
                        message: 'Debe elegir su g\u00E9nero',
                        callback: function (value, validator, $field) {
                            // Get the selected options
                            var options = validator.getFieldElements('genero').val();
                            return (options !== null && options.length >= 1 && options.length <= 10);
                        }
                    }
                }
            },
            
            'confirmar[]': {
                validators: {
                    choice: {
                        min: 1,
                        message: 'Acepte los t\u00E9rminos y condiciones para continuar'
                    }
                }
            },
            fechaNacimiento: {
                validators: {
                    notEmpty: {
                        message: 'La fecha de nacimiento es requerida'
                    },
                    mayorEdad: {
                        message: 'No es mayor de edad'
                    }
                }
            }


        }
    });
});
