const fs = require('fs');

var xmldoc=document.implementation.createDocument('','',null);
xmldoc.async=false;
xmldoc.load('../pom.xml');
var version = xmldoc.getElementsByName("version")[0];
console.info("DodoOpenJavaVersion=" + version.textContent)
fs.writeFileSync(process.env.GITHUB_OUTPUT, 'version=' + version.textContent);