'use strict';

function loadStatus(async, endpoint) {
    const oReq = new XMLHttpRequest();
    oReq.onreadystatechange = function () {
        if (this.readyState === 4) {
            const minecraftServerStatus = JSON.parse(this.responseText);
            updateStatus(minecraftServerStatus);
        }
        resizeFrame();
    };

    oReq.open('GET', endpoint, async);
    oReq.send();
}

function updateStatus(status) {
    const serverName = document.getElementById('server_name');
    serverName.innerText = "Forge minecraft server";
    document.getElementById('title_text_status').innerText = 'Server status:';
    document.getElementById('value_text_status').innerText = (status.online) ? 'online' : 'offline';

    document.getElementById('title_text_desc').innerText = 'Description:';
    document.getElementById('value_text_desc').innerText = status.description;

    if (status.online) {
        const img = document.getElementById('icon');
        img.src = status.icon;
        document.getElementById('title_text_version').innerText = 'Server version:';
        document.getElementById('value_text_version').innerText = status.version;

        document.getElementById('title_text_players_active').innerText = 'Player slots:';
        document.getElementById('value_text_players_active').innerText = status.onlinePlayersCount + ' / ' + status.maxPlayers;

        document.getElementById('title_text_players_active_nicks').innerText = 'Players online:';
        document.getElementById('value_text_players_active_nicks').innerText = status.onlinePlayers.join(', ');

        document.getElementById('mods_btn').onclick = function () {
            showMods(status.mods)
        }
    } else {
        document.getElementById('row_version').remove();
        document.getElementById('row_players_active').remove();
        document.getElementById('row_players_active_nicks').remove();
        document.getElementById('mod_row').remove();
    }
}

function resizeFrame() {
    if (parent.length) {
        setTimeout(function () {
            const parentFrame = parent.document.getElementById(window.name);
            parentFrame.style.height = document.documentElement.offsetHeight + 'px';
            parentFrame.style.width = document.documentElement.scrollWidth + 'px';
        }, 10);
    }
}

function showMods(mods) {
    document.getElementById('mods_btn_wrapper').remove();
    const modRov = document.getElementById('mod_row');
    const ulNode = document.createElement("UL");
    mods.forEach(element => {
        const liNode = document.createElement("LI");
        const textNode = document.createTextNode(element.id + ' : ' + element.version);
        liNode.appendChild(textNode);
        ulNode.appendChild(liNode);
    });
    modRov.appendChild(ulNode);
    resizeFrame();
}
