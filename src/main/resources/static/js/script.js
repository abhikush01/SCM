let currentTheme = getTheme();

console.log(currentTheme);
changeTheme();
//TODO
function changeTheme() {
  //set To web page
  document.querySelector("html").classList.add(currentTheme);
  document.querySelector("#bg-text").textContent =
    currentTheme === "dark" ? "Light" : "Dark";

  //Set The listner
  const changeThemeButton = document.querySelector("#theme_change_button");
  changeThemeButton.addEventListener("click", (e) => {
    document.querySelector("html").classList.remove(currentTheme);
    currentTheme = currentTheme === "light" ? "dark" : "light";
    setTheme(currentTheme);
    document.querySelector("html").classList.add(currentTheme);
    changeThemeButton.querySelector("span").textContent =
      currentTheme === "dark" ? "Light" : "Dark";
  });
}

//SET theme to localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

//Get Theme from localStorage
function getTheme() {
  const theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}
