document.addEventListener("DOMContentLoaded", function() {
  const audio = document.getElementById("audio");
  const playlist = document.getElementById("playlist");
  const prevBtn = document.getElementById("prevBtn");
  const nextBtn = document.getElementById("nextBtn");

  let currentTrackIndex = 0;

  const musicData = [
    { title: "Bryce Vine - Miss You A Little", src: "song1.mp3" },
    { title: "STAY - Steve Void", src: "song2.mp3" },
    { title: "MINE - BAZZI", src: "song3.mp3"}
  ];

  function loadTrack(index) {
    const track = musicData[index];
    audio.src = track.src;
    audio.play();
  }

  function renderPlaylist() {
    playlist.innerHTML = "";
    musicData.forEach((track, index) => {
      const listItem = document.createElement("div");
      listItem.classList.add("playlist-item");
      listItem.textContent = track.title;
      listItem.onclick = () => {
        currentTrackIndex = index;
        loadTrack(currentTrackIndex);
      };
      playlist.appendChild(listItem);
    });
  }

  function changeTrack(direction) {
    currentTrackIndex += direction;
    if (currentTrackIndex < 0) {
      currentTrackIndex = musicData.length - 1;
    } else if (currentTrackIndex >= musicData.length) {
      currentTrackIndex = 0;
    }
    loadTrack(currentTrackIndex);
  }

  prevBtn.addEventListener("click", () => {
    changeTrack(-1);
  });

  nextBtn.addEventListener("click", () => {
    changeTrack(1);
  });

  audio.addEventListener("ended", () => {
    changeTrack(1);
  });

  renderPlaylist();
  loadTrack(currentTrackIndex);
});
