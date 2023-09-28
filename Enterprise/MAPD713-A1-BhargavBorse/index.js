const SERVER_NAME = 'my-server';
const PORT = 5000;
const HOST = '127.0.0.1';

const restify = require('restify');
const server = restify.createServer({ name: SERVER_NAME });

// In-memory storage for JSON payloads
const productData = [];

// Request counters
let getRequestCount = 0;
let postRequestCount = 0;

server.listen(PORT, HOST, function () {
  console.log('Server %s listening at %s', server.name, server.url);
  console.log('**** Resources: ****');
  console.log('********************');
  console.log('/products (GET, POST, DELETE)');
});

server.use(restify.plugins.fullResponse());
server.use(restify.plugins.bodyParser());

// Request logger middleware
server.use(function (req, res, next) {
  console.log(`> ${req.method} ${req.url}: received request`);
  next();
});

// Response logger middleware
server.on('after', function (req, res) {
  console.log(`< ${req.method} ${req.url}: sending response`);
});

// Get all products
server.get('/products', function (req, res, next) {
  getRequestCount++;
  console.log(`Processed Request Count--> Get: ${getRequestCount}, Post: ${postRequestCount}`);
  res.send(200, productData);
});

// Create a new product
server.post('/products', function (req, res, next) {
  postRequestCount++;
  console.log(`Processed Request Count--> Get: ${getRequestCount}, Post: ${postRequestCount}`);

  if (!req.body || !req.body.productId || !req.body.name || !req.body.price || !req.body.quantity) {
    return next(new restify.errors.BadRequestError('Invalid JSON payload'));
  }

  const newProduct = {
    productId: req.body.productId,
    name: req.body.name,
    price: req.body.price,
    quantity: req.body.quantity,
  };

  productData.push(newProduct);
  res.send(201, newProduct);
});

// Delete all products
server.del('/products', function (req, res, next) {
  getRequestCount++;
  console.log(`Processed Request Count--> Get: ${getRequestCount}, Post: ${postRequestCount}`);

  productData.length = 0;
  res.send(204);
});

server.del('/users/:id', function (req, res, next) {
    console.log('POST /users params=>' + JSON.stringify(req.params));
    // Delete the user with the persistence engine
    usersSave.delete(req.params.id, function (error, user) {
  
      // If there are any errors, pass them to next in the correct format
      if (error) return next(new Error(JSON.stringify(error.errors)))
  
      // Send a 204 response
      res.send(204)
    })
  })
