const fs = require('fs');

const xml2js = require('xml2js');

const parser = new xml2js.Parser(undefined);
const xml = fs.readFileSync('pom.xml', 'utf8');
parser.parseString(xml, (err, result) => {
    if (err) {
        console.error(err);
        return;
    }
    let version = result.project.version;
    console.info("DodoOpenJavaVersion=" + version)
    fs.appendFileSync(process.env.GITHUB_ENV, "version=" + version + "\n");
    console.log(result.project.properties)
    fs.appendFileSync(process.env.GITHUB_ENV, "oldversion=" + result.project.properties.oldversion[0]);
});
