/**
 * http://usejsdoc.org/
 */

var SerialPort = require('serialport');
const Readline = SerialPort.parsers.Readline;
var querystring = require('querystring');
var http = require('http');

var port = new SerialPort('com4', {
	baudRate : 9600
});

const parser = new Readline();
port.pipe(parser);
parser.on('data', newproduct);




function newproduct(id) {

	id = id.substring(0, id.length - 1);
	console.log(id)

	
	// An object of options to indicate where to post to
	var post_options = {
	 host: '192.168.0.33',
	 port: '8080',
	 path: '/workpiecearrived',
	 method: 'POST',
	 headers: {
	     'Content-Type': 'application/json',
	     'Content-Length': Buffer.byteLength(id)
	 }
	};
	// Set up the request
	var post_req = http.request(post_options, function(res) {
		res.setEncoding('utf8');
		res.on('data', function(chunk) {
			console.log('Response: ' + chunk);
		});
	});

	// post the data
	post_req.write(id);
	post_req.end();

};

// Open errors will be emitted as an error event
parser.on('error', function(err) {
	console.log('Error: ', err.message);
});