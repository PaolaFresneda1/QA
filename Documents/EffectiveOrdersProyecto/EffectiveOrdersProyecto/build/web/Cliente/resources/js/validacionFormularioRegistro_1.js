
$().ready(function () {

    $('#formularioSolicitudPedido').formValidation({// Validación datos capa cliente. TENER PRESENTE EL ID DEL FORM
        err: {container: 'tooltip'}, //muestra en tooltips
        icon: {valid: 'fa fa-thumbs-up', invalid: 'fa fa-thumbs-down', validating: 'fa fa-refresh'}, //iconos
        //locale: 'es_ES', //idioma - debe enlazar el archivo "es_ES.js"
        fields: {
            
            nombreProducto: {
                validators: {
                    callback: {
                        message: 'Debe elegir un producto',
                        callback: function (value, validator, $field) {
                            // Get the selected options
                            var options = validator.getFieldElements('nombreProducto').val();
                            return (options != null && options.length >= 1 && options.length <= 3);
                        }
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
                            return (options !== null && options.length >= 2 && options.length <= 4);
                        }
                    }
                }
            }


        }
    });
});
