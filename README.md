# ThreadingPerformance
Let's measure different ways to create background tasks in millis

This project just measures the time to create a background task and get its result. Its focused on measuring the time took by creating the process, so we do weird things. Instead of doing 1000 logs inside a thread, we start 1000 sequential threads. Every test is sequential. And here's the result:

- 1000 Threads: 2.481ms
- 1000 ASyncTasks: 1.401ms
- 1000 IntentService + EventBus: 5.487ms
- 1000 Rx Completables: 2.753ms

By the way, this project has been built on a great Schibsted’s Lab Day. A Lab Day is a free day to do whatever you want to improve yourself, your team and the company.
