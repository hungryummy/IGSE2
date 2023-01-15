const checkEmail = (rule, value, callback) => {
    const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    if (!value) {
        return callback(new Error("Email ID could not be null"));
    }
    setTimeout(() => {
        if (mailReg.test(value)) {
            callback();
        } else {
            callback(new Error("please enter valid email address"));
        }
    }, 100);
};
