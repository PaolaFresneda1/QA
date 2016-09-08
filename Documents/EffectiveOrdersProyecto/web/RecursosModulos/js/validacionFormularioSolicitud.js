
$().ready(function () {

    $('#formularioSolicitudPedido').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            nombreProducto: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un producto'
                    }
                }
            },
            cantidadProducto: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese una cantidad valida'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'Solo números'
                    },
                }
            },
            clasificacionProducto: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione una clasificación'
                    }
                }
            }

        }
    });
    
    
    $('#formularioCarrito').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
             direccion: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese una dirección válida'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ#]+$/,
                        message: 'No se aceptan carácteres especiales'
                    },
                    stringLength: {
                        min: 10,
                        message: 'Mínimo 10 carácteres'
                    }
                }
            },
            fEntrega: {
                validators: {
                    
                    date: {
                        message: 'Seleccione una fecha',
                        format: 'YYYY/MM/DD'
                    }
                }
            },
            
            comentariosPedido: {
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese un comentario válido'
                    },
                    regexp: {
                        regexp: /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9/\s/a-zA-ZñÑáéíóúÁÉÍÓÚ,.;:]+$/,
                    },
                    stringLength: {
                        min: 10,
                        message: 'Minimo 10 caracteres'
                    }
                }
            }

        }
    });
    
    $('#formularioEditarPedido').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            nombreProducto: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un producto'
                    }
                }
            },
            cantidadProducto: {//Validar con los aributos NAME de cada INPUT
                row: '.form-group',
                validators: {
                    notEmpty: {
                        message: 'Ingrese una cantidad valida'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'Solo números'
                    },
                }
            },
            clasificacionProducto: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione una clasificación'
                    }
                }
            }

        }
    });
    
});
