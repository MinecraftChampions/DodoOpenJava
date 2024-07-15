const fs = require('fs');

let convert = require('xml-js');

let xml = fs.readFileSync("../pom.xml",'utf-8');
let result = convert.xml2json(xml, {compact: true, spaces: 4});
const json = JSON.parse(result);
const version = json.project.version;
console.info("DodoOpenJavaVersion=" + version.textContent)
fs.writeFileSync(process.env.GITHUB_OUTPUT, 'version=' + version.textContent);