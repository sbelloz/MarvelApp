# MarvelApp [![Build Status](https://travis-ci.org/sbelloz/MarvelApp.svg?branch=master)](https://travis-ci.org/sbelloz/MarvelApp)

[Wallapop](https://www.wallapop.com/) Android Tech test, listing Captain America's comics in Master/detail app using to [Marvel Api](https://developer.marvel.com/)

## Screenshots

![Mobile list](art/mobile_detail.png) 
![Mobile list](art/mobile_list.png)
![Mobile list](art/tablet_list.png)

## Architecture

* **Clean Architecture**
* **MVP**
* **Dependency Injection** with Dagger 2
* **Reactive Programming** with RxJava

## Dependencies

* [RxJava](https://github.com/ReactiveX/RxJava)
* [Retrofit](https://github.com/square/retrofit)
* [Dagger 2](https://github.com/google/dagger)
* [Gson](https://github.com/google/gson)
* [Butterknife](https://github.com/JakeWharton/butterknife)
* [Glide](https://github.com/bumptech/glide)
* [LeakCanary](https://github.com/square/leakcanary)

## TODO

- [ ] Cache comics
- [ ] Orientation change, App in background, retain Presenters

## License

```
Copyright © 2017 Simone Bellotti

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```