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
    fs.writeFileSync(process.env.GITHUB_OUTPUT, 'version=' + version+"\n\n");
    let oldversion = result.project.properties.oldversion;
    console.info("oldversion=" + oldversion)
    fs.appendFileSync(process.env.GITHUB_OUTPUT, 'oldversion=' + oldversion);
});
