<template>
  <div class="class-table-container">
    <van-nav-bar title="我的课程表" left-arrow @click-left="onClickLeft" :border="false" />
    <van-dropdown-menu active-color="#1989fa">
      <van-dropdown-item
        v-model="value1"
        :options="option1"
        get-container="body"
        @change="selectedAcademic"
      />
      <van-dropdown-item
        v-model="value2"
        :options="option2"
        get-container="body"
        @change="selectedSemester"
      />
    </van-dropdown-menu>
    <van-tabs
      v-model="active"
      sticky
      animated
      swipeable
      color="#4B9EF8"
      title-active-color="#4B9EF8"
      title-inactive-color="#707070"
      background="#F9F9F9"
    >
      <!-- 周一到周日课程 -->
      <!-- 周一 -->
      <van-tab title="周一">
        <div class="lesson-container">
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '1'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周二">
        <div class="lesson-container">
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '2'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周三">
        <div class="lesson-container">
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '3'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周四">
        <div class="lesson-container">
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '4'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周五">
        <div class="lesson-container">
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '1'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周六">
        <div class="lesson-container">
          <van-empty description="今天没课啦~" v-if="classTableData.courses[5] && classTableData.courses[6]"/>
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '6'">
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="周日">
        <div class="lesson-container">
          <van-empty description="今天没课啦~" v-if="classTableData.courses[5] && classTableData.courses[6]"/>
          <div v-for="item in classTableData.courses" :key="item.id">
            <div v-for="items in item" :key="items.id">
              <div class="lesson-item" v-if="items.kcmc && items.xqj === '7'">
                <van-empty description="今天没课啦~" v-if="items.kcmc === ''"/>
                <span class="jcs-bg"></span>
                <div class="lesson-message">
                  <h3>{{ items.kcmc }}</h3>
                  <div class="lesson-detail">
                    <!-- 课程节数 -->
                    <span v-if="items.jcs === '1' & items.row === '2'">
                      <p>第{{ jcsList[0].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '3'">
                      <p>第{{ jcsList[1].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '4'">
                      <p>第{{ jcsList[2].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '1' & items.row === '5'">
                      <p>第{{ jcsList[3].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '2'">
                      <p>第{{ jcsList[4].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '3' & items.row === '3'">
                      <p>第{{ jcsList[5].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '4' & items.row === '2'">
                      <p>第{{ jcsList[6].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '2'">
                      <p>第{{ jcsList[7].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '6' & items.row === '4'">
                      <p>第{{ jcsList[8].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '8' & items.row === '2'">
                      <p>第{{ jcsList[9].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '2'">
                      <p>第{{ jcsList[10].jcs }}节</p>
                    </span>
                    <span v-if="items.jcs === '10' & items.row === '3'">
                      <p>第{{ jcsList[11].jcs }}节</p>
                    </span>
                    <p>@{{ items.cdmc }}</p>
                    <p>{{ items.xm }}</p>
                  </div>
                </div>
                <div class="lesson-time">
                  <span v-if="items.jcs === '1' & items.row === '2'">
                    <p>{{ jcsList[0].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '3'">
                    <p>{{ jcsList[1].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '4'">
                    <p>{{ jcsList[2].time }}</p>
                  </span>
                  <span v-if="items.jcs === '1' & items.row === '5'">
                    <p>{{ jcsList[3].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '2'">
                    <p>{{ jcsList[4].time }}</p>
                  </span>
                  <span v-if="items.jcs === '3' & items.row === '3'">
                    <p>{{ jcsList[5].time }}</p>
                  </span>
                  <span v-if="items.jcs === '4' & items.row === '2'">
                    <p>{{ jcsList[6].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '2'">
                    <p>{{ jcsList[7].time }}</p>
                  </span>
                  <span v-if="items.jcs === '6' & items.row === '4'">
                    <p>{{ jcsList[8].time }}</p>
                  </span>
                  <span v-if="items.jcs === '8' & items.row === '2'">
                    <p>{{ jcsList[9].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '2'">
                    <p>{{ jcsList[10].time }}</p>
                  </span>
                  <span v-if="items.jcs === '10' & items.row === '3'">
                    <p>{{ jcsList[11].time }}</p>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import timeData from '@/data/lessonTime.json'
export default {
  name: 'SchoolTimeTable',
  data () {
    return {
      value1: 3,
      value2: 2,
      active: 0,
      yearsData: '',
      semesterData: '',
      jcsList: timeData,
      year: '',
      semester: '',
      option1: [
        { text: '请选择学年', value: 0 },
        { text: '2019-2020', value: 1 },
        { text: '2020-2021', value: 2 },
        { text: '2021-2022', value: 3 },
        { text: '2023-2024', value: 4 },
        { text: '2024-2025', value: 5 }
      ],
      option2: [
        { text: '请选择学期', value: 0 },
        { text: '1', value: 1 },
        { text: '2', value: 2 }
      ],
      classTableData: {
        courses: [],
        coursesArr: []
      }
    }
  },
  created () {
    this.year = '2021'
    this.semester = '2'
  },
  mounted () {
    this.getCourseData()
  },
  methods: {
    onClickLeft () {
      this.$router.push('home')
    },
    // 选择学年
    selectedAcademic () {
      switch (this.value1) {
        case 1:
          this.year = '2019'
          break
        case 2:
          this.year = '2020'
          break
        case 3:
          this.year = '2021'
      }
    },
    // 选择学期
    selectedSemester () {
      switch (this.value2) {
        case 1:
          this.semester = '1'
          break
        case 2:
          this.semester = '2'
          break
      }
      this.getCourseData()
    },
    // 请课表数据
    async getCourseData () {
      await this.axios({
        methods: 'get',
        url: 'https://www.mzkt.xyz:443/smart-school/education/queryClasses',
        params: {
          year: this.year,
          semester: this.semester
        }
      }).then(res => {
        // 获取课程数据
        this.classTableData.courses = res.data.kbList
        this.yearsData = localStorage.setItem('year', JSON.stringify(this.year))
        this.semesterData = localStorage.setItem('semester', JSON.stringify(this.semester))
        this.classTableData.coursesArr = localStorage.setItem('coursesData', JSON.stringify(this.classTableData.courses))
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="less" scoped>
.class-table-container {
  padding-bottom: 20px;
  z-index: -1;
  height: 100vh;
}

.lesson-container {
  width: 100%;
  height: 82vh;
  background-color: #fff;
  padding-top: 10px;
}

.lesson-item {
  display: flex;
  width: 100%;
  align-items: center;
  border-left: 5px solid #4b9ef8;
  margin-bottom: 20px;
}

.lesson-message {
  flex: 0.7;
  text-align: start;
  padding-left: 12px;
}

.lesson-message h3 {
  font-size: 17px;
  margin-top: 2px;
}

.lesson-message p {
  margin-right: 12px;
  color: rgb(110, 110, 110);
  font-size: 14px;
}

.lesson-detail {
  display: flex;
  align-items: center;
  margin-top: -22px;
  margin-bottom: -13px;
}

.lesson-time {
  flex: 0.3;
  text-align: center;
  color: #4b9ef8;
  font-size: 16px;
  padding-right: 5px;
}
</style>
