# Deep Medicine

An android application for detecting malaria using **Tensorflow 2.2.0** and **Tensorflow Lite**.

The model has been trained on more than **27000**, [dataset is here](https://www.kaggle.com/iarunava/cell-images-for-detecting-malaria), images of uninfected and parasitized blood cells using Convolutional Neural Networks (**CNN**).

While training, different techniques, such as **transfer learning** and **data augmentation** have been used.

The model uses **MobileNetV2** architecture for base model and adds classifier on top of it.

Then, trained model has been converted into **.tflite** version using Tensorflow Lite for running it in android platform.

The model has following metrics for test set:
* Accuracy - 96.07
* Recall - 98.79
* Precision - 93.44
* F1 Score - 96.04 




 