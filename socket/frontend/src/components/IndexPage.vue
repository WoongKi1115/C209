<template>
  <div>
    <header>
      <h1>Soom</h1>
      <button id="someREQ">REQ to server</button>
    </header>
    <main>
      <div id="welcome">
        <form action="">
          <input type="text" />
          <button>Enter room</button>
        </form>
      </div>
      <div id="call">
        <div id="mtStream">
          <video id="myFace" src=""></video>
          <button id="mute">MUTE</button>
          <button id="camera">Turn Camera off</button>
          <select name="" id="cameras"></select>
          <video id="peerStream" src="" autoplay></video>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
    };
  },
  created() {
    const socket = io();

    const myFace = document.getElementById("myFace");
    const muteBtn = document.getElementById("mute");
    const cameraBtn = document.getElementById("camera");
    const camerasSelect = document.getElementById("cameras");

    const welcome = document.getElementById("welcome");
    const call = document.getElementById("call");
    const welcomeForm = welcome.querySelector("form");

    const reqDjango = document.getElementById("someREQ");

    call.hidden = true;

    let myStream;
    let muted = false;
    let cameraOff = false;
    let roomName;
    let myPeerConnection;

    async function getCamera() {
      try {
        // navigator : 브라우저에 대한 기기, 연결, 버전 등의 정보를 저장하는 객체
        const devices = await navigator.mediaDevices.enumerateDevices();
        const cameras = devices.filter(
          (device) => device.kind === "videoinput"
        );

        const currentCamera = myStream.getVideoTracks()[0];
        cameras.forEach((camera) => {
          const option = document.createElement("option");
          // camera의 이름(label)을 옵션으로 보여줌
          option.innerText = camera.label;
          option.value = camera.deviceId;
          if (currentCamera === camera.label) {
            option.selected = true;
          }
          camerasSelect.appendChild(option);
        });
      } catch (e) {
        console.log(e);
      }
    }

    async function getMedia(deviceId) {
      const initialConstrains = {
        audio: true,
        video: { facingMode: "user" },
      };
      const cameraConstraints = {
        audio: true,
        video: { deviceId: { exact: deviceId } },
      };
      try {
        myStream = await navigator.mediaDevices.getUserMedia(
          deviceId ? cameraConstraints : initialConstrains
        );
        myFace.srcObject = myStream;
        if (!deviceId) {
          await getCamera();
        }
      } catch (e) {
        console.log(e);
      }
    }

    // getMedia();

    function handleMuteClick() {
      myStream.getAudioTracks().forEach((track) => {
        track.enabled = !track.enabled;
      });
      if (!muted) {
        muteBtn.innerText = "Unmute";
        muted = true;
      } else {
        muteBtn.innerText = "Mute";
        muted = false;
      }
    }

    function handleCameraClick() {
      myStream.getVideoTracks().forEach((track) => {
        track.enabled = !track.enabled;
      });
      if (cameraOff) {
        cameraBtn.innerText = "Turn Camera Off";
        cameraOff = false;
      } else {
        cameraBtn.innerText = "Turn Camera On";
        cameraOff = true;
      }
    }

    async function handleCameraChange() {
      await getMedia(camerasSelect.value);
      if (myPeerConnection) {
        const videoTrack = myStream.getVideoTracks()[0];
        const videoSender = myPeerConnection
          .getSender()
          .find((sender) => sender.track.kind === "video");
        videoSender.replaceTrack(videoTrack);
      }
    }

    async function handleWelcomeSubmit(event) {
      event.preventDefault();
      const input = welcomeForm.querySelector("input");
      await initCall();
      socket.emit("join_room", input.value);
      roomName = input.value;
      input.value = "";
    }

    async function initCall() {
      welcome.hidden = true;
      call.hidden = false;
      await getMedia();
      makeRTCConnection();
    }

    function handleIce(data) {
      socket.emit("ice", data.candidate, roomName);
    }

    function handleAddStream(data) {
      const peerFace = document.getElementById("peerStream");
      peerFace.srcObject = data.streams[0];
    }

    function makeRTCConnection() {
      myPeerConnection = new RTCPeerConnection({
        //STUN provided by google
        iceServers: [
          {
            urls: [
              "stun:stun.l.google.com:19302",
              "stun:stun1.l.google.com:19302",
              "stun:stun2.l.google.com:19302",
              "stun:stun3.l.google.com:19302",
              "stun:stun4.l.google.com:19302",
            ],
          },
        ],
      });
      myPeerConnection.addEventListener("icecandidate", handleIce);
      myPeerConnection.addEventListener("track", handleAddStream);
      myStream
        .getTracks()
        .forEach((track) => myPeerConnection.addTrack(track, myStream));
    }

    // import axios from "axios";

    function reqToDjango() {
      axios({
        url: "http://localhost:8000/appsoom/some/",
        method: "get",
      })
        .then((res) => {
          console.log(res);
          console.log("hello");
        })
        .catch((err) => {
          console.log("not connect with Django");
        });
    }

    muteBtn.addEventListener("click", handleMuteClick);
    cameraBtn.addEventListener("click", handleCameraClick);
    camerasSelect.addEventListener("input", handleCameraChange);
    welcomeForm.addEventListener("submit", handleWelcomeSubmit);

    reqDjango.addEventListener("click", reqToDjango);

    socket.on("welcome", async () => {
      const offer = await myPeerConnection.createOffer();
      myPeerConnection.setLocalDescription(offer);
      socket.emit("offer", offer, roomName);
    });

    socket.on("offer", async (offer) => {
      myPeerConnection.setRemoteDescription(offer);
      const answer = await myPeerConnection.createAnswer();
      myPeerConnection.setLocalDescription(answer);
      socket.emit("answer", answer, roomName);
    });

    socket.on("answer", (answer) => {
      myPeerConnection.setRemoteDescription(answer);
    });

    socket.on("ice", (ice) => {
      myPeerConnection.addIceCandidate(ice);
    });
  },
  methods() {},
};
</script>

<style>
</style>