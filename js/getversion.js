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
    fs.writeFileSync(process.env.GITHUB_OUTPUT, 'version=' + version);
    let oldVersion = result.project.properties.oldVersion;
    console.info("OldVersion=" + oldVersion)
    fs.writeFileSync(process.env.GITHUB_OUTPUT, 'oldVersion=' + oldVersion);
});
