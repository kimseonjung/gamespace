function registCheck() {
    document.getElementsByName("userAddress")[0].value =
        document.getElementsByName("regist-zipcode")[0].value + '&' +
        document.getElementsByName("regist-address1")[0].value + '&' +
        document.getElementsByName("regist-address2")[0].value;

    return true;
}