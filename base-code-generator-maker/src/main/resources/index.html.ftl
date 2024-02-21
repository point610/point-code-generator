<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Barrage Interaction</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    margin: 0;
    padding: 0;
    overflow: hidden;
  }
  .barrage-container {
    position: absolute;
    top: 0%;
    transform: translateY(10%);
    overflow: hidden;
    width: 100%;
    height: 80%;
  }
  .barrage {
    position: absolute;
    white-space: nowrap;
    font-size: 20px;
    color: #333;
    animation: moveBarrage linear 10s infinite;
  }
  @keyframes moveBarrage {
    0% {
      transform: translateX(800%);
    }
    100% {
      transform: translateX(-100%);
    }
  }
</style>
</head>
<body>
<div class="barrage-container">
</div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var container = document.querySelector('.barrage-container');

    var barrageTexts = [
      '这个世界怎么这么无聊，连路边的草都比我的生活有趣。',
      '无聊的日子就像脱了皮的苹果，一口咬下去全是空虚。',
      '无聊的时候就是脑袋里面有一群苍蝇在飞，乱七八糟的叫唤，却又无处安放。',
      '人生就像一个无聊的游戏，每一关都是重复的折磨，而奖励只是更多的无聊。',
      '无聊的时候连闻一下空气都能闻到寂寞的味道。',
      '${mywords}',
      '${mywords}',
    ];

    var usedPositions = {};

    function createBarrage() {
      var barrage = document.createElement('div');
      barrage.classList.add('barrage');
      var randomIndex = Math.floor(Math.random() * barrageTexts.length);
      barrage.innerText = barrageTexts[randomIndex];
      var position;
      do {
        position = Math.random() * 100 + '%';
      } while (usedPositions[position]);
      usedPositions[position] = true;
      barrage.style.top = position;
      container.appendChild(barrage);
      var duration = Math.random() * 10 + 5; // Random duration between 5 to 15 seconds
      barrage.style.animationDuration = duration + 's';

      // Remove the barrage after its animation completes
      barrage.addEventListener('animationend', function() {
        barrage.remove();
        usedPositions[position] = false;
      });
    }

    // Generate barrage every 2 seconds
    setInterval(createBarrage, 2000);
  });
</script>
</body>
</html>
