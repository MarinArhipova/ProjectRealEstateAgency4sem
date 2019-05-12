$(document).ready(function () {
        var category;
        $(".category").click(function () {
            category = $(this).data('category');
            $.ajax({
                type: 'get',
                url: '/shop/json',
                data: {
                    // category: $(this).attr("data-category")
                    category: $('.category').data('category')
                },
                beforeSend: function () {
                    $(".product-item").remove();
                }
                // }).done(
                //     function (products) {
                //         // $('#loader').hide();
                //         // console.log(products);
                //         console.log($('.category').data('category'));
                //         if (category === "newbuild") printCommons(products);
                //         else if (category === "resale") printCommons(products);
                //         else if (cateogory === "rental") printCommons(products);
                //         else if (cateogory === "commerc") printCommerces(products);
                //         else if (cateogory === "cottage") printCottages(products);

                // if ($(this).attr("data-category") === "newbuild") printCommons(products);
                // else if ($(this).attr("data-category") === "resale") printCommons(products);
                // else if ($(this).attr("data-category") === "rental") printCommons(products);
                // else if ($(this).attr("data-category") === "commerc") printCommerces(products);
                //         // else if ($(this).attr("data-category")==="cottage") printCottages(products);
                //     }
                // )
            });

            function printProducts(products) {
                $.each(products, function (index, product) {
                    console.log(product);
                    printProduct(product);
                })
            }

            // function printCommerces(products) {
            //     $.each(products, function (index, product) {
            //         console.log(product);
            //         printCommerc(product);
            //     })
            // }
            //
            // function printCottages(products) {
            //     $.each(products, function (index, product) {
            //         console.log(product);
            //         printCottage(product);
            //     })
            // }

            function printProduct(product) {
                let result = "<div class=\"product-item\">\n" +
                    "                                <div class=\"row\" style=\"margin: 0; \">\n" +
                    "                                    <div class=\"col-5\" style=\"padding: 0 0 0 80px;\"><img src=\"" + product["img"] + "\"\n" +
                    "                                                                                         style=\"width: 200px; height: 200px; text-align: right\">\n" +
                    "                                    </div>\n" +
                    "                                    <div class=\"col-7\" style=\"padding:30px 0 0 0\">\n" +
                    "                                        <h3>" + product["title"] + "</h3>\n" +
                    // "                                        <span class=\"price\">Количество комнат:" + product["countOfRooms"] + "</span>\n" +
                    "                                        <span class=\"price1\">Стоимость: " + product["price"] + "руб.</span>\n" +
                    "                                        <input type=\"submit\" class=\"submit_feedback\" data-id=\"" + product["id"] + product["category"] + "\n" +
                    "                                               value=\"Добавить\">\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>";
                $(".products").append(result)
            }

            // function printCommerc(product) {
            //     // let title = product["title"] === undefined ? " " : product["title"];
            //     // let type = product["type"] === undefined ? " " : product["type"];
            //     // let price = product["price"] === undefined ? " " : product["price"] + " руб.";
            //     let result = "<div class=\"product-item\">\n" +
            //         "                                <div class=\"row\" style=\"margin: 0; \">\n" +
            //         "                                    <div class=\"col-5\" style=\"padding: 0 0 0 80px;\"><img src=\"" + product["img"] + "\"\n" +
            //         "                                                                                         style=\"width: 200px; height: 200px; text-align: right\">\n" +
            //         "                                    </div>\n" +
            //         "                                    <div class=\"col-7\" style=\"padding:30px 0 0 0\">\n" +
            //         "                                        <h3>" + product["title"] + "</h3>\n" +
            //         "                                        <span class=\"price\">Тип помещения:" + product["type"] + "</span>\n" +
            //         "                                        <span class=\"price1\">Стоимость: " + product["price"] + "руб.</span>\n" +
            //         "                                        <input type=\"submit\" class=\"submit_feedback\" data-id=\"" + product["id"]+product["category"] + "\n" +
            //         "                                               value=\"Добавить\">\n" +
            //         "                                    </div>\n" +
            //         "                                </div>\n" +
            //         "                            </div>";
            //     $(".products").append(result)
            // }

            // function printCottage(product) {
            //     let result = "<div class=\"product-item\">\n" +
            //         "                                <div class=\"row\" style=\"margin: 0; \">\n" +
            //         "                                    <div class=\"col-5\" style=\"padding: 0 0 0 80px;\"><img src=\"" + product["img"] + "\"\n" +
            //         "                                                                                         style=\"width: 200px; height: 200px; text-align: right\">\n" +
            //         "                                    </div>\n" +
            //         "                                    <div class=\"col-7\" style=\"padding:30px 0 0 0\">\n" +
            //         "                                        <h3>" + product["title"] + "</h3>\n" +
            //         "                                        <span class=\"price\">Площадь:" + product["square"] + "</span>\n" +
            //         "                                        <span class=\"price1\">Стоимость: " + product["price"] + "руб.</span>\n" +
            //         "                                        <input type=\"submit\" class=\"submit_feedback\" data-id=\"" + product["id"]+product["category"] + "\n" +
            //         "                                               value=\"Добавить\">\n" +
            //         "                                    </div>\n" +
            //         "                                </div>\n" +
            //         "                            </div>";
            //     $(".products").append(result)
            // }

        });
    }
)