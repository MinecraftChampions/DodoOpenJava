const request = require('request');

const version = process.env.version;
console.log(version)
console.log("开始自动获取tag")
const tagsUrl = 'https://api.github.com/repos/MinecraftChampions/DodoOpenJava/tags';
const o = {
    url: tagsUrl,
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'User-Agent': 'Request',
        'X-platform': 'Node'
    }

};
request(o, (err, res, body) => {
    if (err) {
        return console.log(err);
    }
    let data = JSON.parse(body);
    if (data.length === 1) {
        oldVersion = getEarliestCommit();
    } else {
        let checked = false;
        for (const t of data) {
            if (checked) {
                oldVersion = t.name;
                console.log("检测到旧版本:" + t.name)
                break;
            } else {
                if (t.name === version) {
                    checked = true;
                }
            }
        }
    }
    getChange();
});

function getChangeLog(commits) {
    const typeMessageRe =
        /^(feat|fix|docs|style|refactor|perf|test|chore|revert|build|ci|wip|merge|release|hotfix|deps): /
    const result = {};
    for (const commit of commits) {
        let message = commit.message;
        if (message.indexOf('\n') !== -1) {
            message = message.split('\n')[0];
        }
        const index = message.search(typeMessageRe);
        let type = '';
        const sha = commit.sha.substring(0, 7);
        if (index === -1) {
            type = "others";
            message = sha + ": " + message + '([' + commit.commiter
                + '](https://github.com/MinecraftChampions/DodoOpenJava/commit/' +
                commit.sha + '))'
            if (type in result) {
                let messages = result[type];
                messages.push(message);
            } else {
                result[type] = [message];
            }
        } else {
            const temp = message.split(': ');
            type = temp[0];
            const str = message.replace(typeMessageRe, '');
            message = sha + ": " + str + '([' + commit.commiter
                + '](https://github.com/MinecraftChampions/DodoOpenJava/commit/' +
                commit.sha + '))'
            if (type in result) {
                let messages = result[type];
                messages.push(message);
            } else {
                result[type] = [message];
            }
        }
    }
    return result;
}

function getEarliestCommit() {
    console.log("没有检测到更多的tag,开始获取第一个提交")
    const url = 'https://api.github.com/repos/MinecraftChampions/DodoOpenJava/commits';
    let commit = '';
    let options = {
        url: url,
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'User-Agent': 'Request',
            'X-platform': 'Node'
        }
    };
    request(options, (err, res, body) => {
        if (err) {
            return console.log(err);
        }
        let data = JSON.parse(body);
        commit = data[data.length - 1].sha;
        console.log("检测到旧版本:" + oldVersion)
    });
    return commit;
}

function getChange() {
    const url = 'https://api.github.com/repos/MinecraftChampions/DodoOpenJava/compare/'
        + oldVersion + '...' + version;

    let options = {
        url: url,
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'User-Agent': 'Request',
            'X-platform': 'Node'
        }
    };
    request(options, (err, res, body) => {
        const commits = [];
        if (err) {
            return console.log(err);
        }
        let data = JSON.parse(body);
        let cs = data.commits;
        cs.forEach(commit => {
            commits.push({
                message: commit.commit.message,
                commiter: commit.commit.committer.name,
                sha: commit.sha
            })
        });
        console.log("检测到" + commits.length + "条提交");
        startParsing(commits)
    });
}

function startParsing(commits) {
    console.log("开始解析")
    const result = getChangeLog(commits);
    const keys = Object.keys(result);
    let hasOthers = false;
    let content = "";
    for (let key of keys) {
        if (key === "others") {
            hasOthers = true;
            continue;
        }
        let c = '';
        let messages = result[key];
        c = '## ' + key;
        for (let message of messages) {
            c = c + "\n- " + message;
        }
        content = content + c + "\n\n";
    }
    if (hasOthers) {
        let key = "others";
        let c = '';
        let messages = result[key];
        c = '## ' + key;
        for (let message of messages) {
            c = c + "\n- " + message;
        }
        content = content + c;
    }
    release(content)
}

function release(content) {
    const token = process.env.repo_token;
    const url = 'https://api.github.com/repos/MinecraftChampions/DodoOpenJava/releases';

    let options = {
        url: url,
        method: 'POST',
        json: true,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/vnd.github+json',
            'User-Agent': 'Request',
            'X-platform': 'Node',
            'Authorization': "Bearer " + token
        },
        body: {
            tag_name: version,
            name: process.env.release_title,
            body: content,
            draft: false,
            prerelease: false
        }
    };

    request(options, (err, res, body) => {
        if (err) {
            return console.log(err);
        }
        console.log(body);
    })
}