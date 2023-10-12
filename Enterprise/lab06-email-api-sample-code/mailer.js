// Example code for integration with 
// Test Email API from https://mailtrap.io/
const nodemailer = require("nodemailer");
async function main() {
  var transport = nodemailer.createTransport({
    host: "sandbox.smtp.mailtrap.io",
    port: 2525,
    auth: {
      user: "8bee5689aa770c",
      pass: "43db5d7d95b7a6"
    }
  });
  // send mail with defined transport object
  let info = await transport.sendMail({
    from: '"Fred Foo" <foo@example.com>', // sender address
    to: "bar@example.com, baz@example.com", // list of receivers
    subject: "Hello", // Subject line
    text: "Hello world?", // plain text body
    html: "<b>Hello world?</b>", // html body
  });
  console.log("Message sent: %s", info.messageId);  
}

main().catch(console.error);