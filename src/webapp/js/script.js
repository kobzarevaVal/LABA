const arrayXC = JSON.parse(`[
	{
		"name":"name_1",
		"season_count":4,
		"rating":4.7
	},
	{
		"name":"name_2",
		"season_count":5,
		"rating":5.0
	}
]`)


var i =0;
function Serial(id, name, season_count, rating, descriprion) {
	this.id = id;
	this.name = name;
	this.season_count = season_count;
	this.rating = rating;
	this.descriprion = descriprion;
}
const serials = new Array();

const mainContainerView = document.querySelector(".searche-item-list");

for (let index of arrayXC) {
	addSerial(arrayXC[i].name, arrayXC[i].season_count, arrayXC[i].rating);
}

function addNewSerial() {
	const name = document.getElementById("inputNameId").value;
	const season_count = document.getElementById("inputSeasonCountId").value;
	const rating = document.getElementById("inputRatingId").value;
	if (name.length > 0 && season_count.length > 0 && rating.length > 0) {
		addSerial(name, season_count, rating);

		document.getElementById("inputNameId").value = "";
		document.getElementById("inputSeasonCountId").value = "";
		document.getElementById("inputRatingId").value = "";
	}
}

function addSerial(name, season_count, rating) {
	const newSerial = new Serial(i++, name, season_count, rating);
	serials.push(newSerial);
	createView(newSerial);
}

function createView(serial) {

	const containerView = document.createElement("div");
	const nameView = document.createElement("h3");
	const seasonCountView = document.createElement("h4");
	const ratingView = document.createElement("h4");

	const nameText = document.createTextNode("Name: " + serial.name);
	const seasonCountText = document.createTextNode("Season count: " + serial.season_count);
	const ratingText = document.createTextNode("Rating: " + serial.rating);

	nameView.appendChild(nameText);
	seasonCountView.appendChild(seasonCountText);
	ratingView.appendChild(ratingText);

	containerView.appendChild(nameView);
	containerView.appendChild(seasonCountView);
	containerView.appendChild(ratingView);

	containerView.setAttribute("class", "serial card");
	containerView.setAttribute("id", serial.id);
	containerView.setAttribute("onclick", "onClickItem("+serial.id+")");

	mainContainerView.appendChild(containerView);
}

function searchSerial() {
	const searcheName = document.getElementById("testasdasd").value;

	const newList = new Array();

	for (let index in serials) {
		if (serials[index].name.includes(searcheName)) {
			newList.push(serials[index]);
		}
	}

	updateList(newList)
}

function updateList(newList) {
	while (mainContainerView.firstChild) {
		mainContainerView.removeChild(mainContainerView.firstChild);
	}

	for (let index in newList) {
		createView(newList[index]);
	}
}

function onClickItem(itemId) {
	clearContentView();
	const item = getById(itemId);

	const nameViewContent = document.getElementById("serialNameContentId");
	const seasonCountAndRatingViewContent = document.getElementById("serialSeasonCountAndRatingContentId");

	nameViewContent.appendChild(document.createTextNode(item.name));

	seasonCountAndRatingViewContent.appendChild(document.createTextNode(`Season count: ${item.season_count}`));
	seasonCountAndRatingViewContent.appendChild(document.createElement("br"));
	seasonCountAndRatingViewContent.appendChild(document.createTextNode(`Rating: ${item.rating}`));

	document.getElementById("serialDescriprionContentId").value = item.descriprion;

	document.getElementById("saveContentButtonId").value = item.id;
	document.getElementById("deleteContentButtonId").value = item.id;
}

function getById(id) {
	for (let index in serials) {
		if (serials[index].id == id) {
			return serials[index];
		}
	}
}

function onClickSaveItem() {
	const itemId = document.getElementById("saveContentButtonId").value;
	const descriptionText = document.getElementById("serialDescriprionContentId").value;
	for (let index in serials) {
		if (serials[index].id == itemId) {
			serials[index].descriprion = descriptionText;
			break;
		}
	}

	clearContentView();
}

function onClickRemoveItem() {
	const itemId = document.getElementById("deleteContentButtonId").value;
	for (let index in serials) {
		if (serials[index].id == itemId) {
			mainContainerView.removeChild(document.getElementById(itemId));
			serials.splice(index, 1);
			break;
		}
	}

	clearContentView();
}

function clearContentView() {
	const nameViewContent = document.getElementById("serialNameContentId");
	while (nameViewContent.firstChild) {
		nameViewContent.removeChild(nameViewContent.firstChild);
	}

	const seasonCountAndRatingViewContent = document.getElementById("serialSeasonCountAndRatingContentId");
	while (seasonCountAndRatingViewContent.firstChild) {
		seasonCountAndRatingViewContent.removeChild(seasonCountAndRatingViewContent.firstChild);
	}

	document.getElementById("serialDescriprionContentId").value = "";

	document.getElementById("saveContentButtonId").value = "";
	document.getElementById("deleteContentButtonId").value = "";
}